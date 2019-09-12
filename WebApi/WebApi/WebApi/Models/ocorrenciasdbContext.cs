using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace WebApi.Models
{
    public partial class ocorrenciasdbContext : DbContext
    {
        public ocorrenciasdbContext()
        {
        }

        public ocorrenciasdbContext(DbContextOptions<ocorrenciasdbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<AreaAtendimento> AreaAtendimento { get; set; }
        public virtual DbSet<AreaAtuacao> AreaAtuacao { get; set; }
        public virtual DbSet<Cidade> Cidade { get; set; }
        public virtual DbSet<Endereco> Endereco { get; set; }
        public virtual DbSet<Estado> Estado { get; set; }
        public virtual DbSet<EstadoOcorrencia> EstadoOcorrencia { get; set; }
        public virtual DbSet<HistoricoOcorrencia> HistoricoOcorrencia { get; set; }
        public virtual DbSet<HistoricoSenha> HistoricoSenha { get; set; }
        public virtual DbSet<ImagemOcorrencia> ImagemOcorrencia { get; set; }
        public virtual DbSet<ImagemUsuario> ImagemUsuario { get; set; }
        public virtual DbSet<Ocorrencia> Ocorrencia { get; set; }
        public virtual DbSet<PrioridadeOcorrencia> PrioridadeOcorrencia { get; set; }
        public virtual DbSet<TelefoneAreaatendimento> TelefoneAreaatendimento { get; set; }
        public virtual DbSet<TelefoneUsuario> TelefoneUsuario { get; set; }
        public virtual DbSet<TipoOcorrencia> TipoOcorrencia { get; set; }
        public virtual DbSet<Usuario> Usuario { get; set; }
        public virtual DbSet<UsuarioAreaatendimento> UsuarioAreaatendimento { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseNpgsql("Server=localhost;Database=ocorrenciasdb;Port=5432;User Id=postgres;Password=admin;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<AreaAtendimento>(entity =>
            {
                entity.HasKey(e => e.CdAreaatendimento);

                entity.ToTable("area_atendimento");

                entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

                entity.Property(e => e.CdAreaatuacao).HasColumnName("cd_areaatuacao");

                entity.Property(e => e.DsAreaatendimento)
                    .IsRequired()
                    .HasColumnName("ds_areaatendimento")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DsEmail)
                    .IsRequired()
                    .HasColumnName("ds_email")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.HasOne(d => d.CdAreaatuacaoNavigation)
                    .WithMany(p => p.AreaAtendimento)
                    .HasForeignKey(d => d.CdAreaatuacao)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("area_atuacao_area_atendimento_fk");
            });

            modelBuilder.Entity<AreaAtuacao>(entity =>
            {
                entity.HasKey(e => e.CdAreaatuacao);

                entity.ToTable("area_atuacao");

                entity.Property(e => e.CdAreaatuacao).HasColumnName("cd_areaatuacao");

                entity.Property(e => e.DsAreaatuacao)
                    .IsRequired()
                    .HasColumnName("ds_areaatuacao")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");
            });

            modelBuilder.Entity<Cidade>(entity =>
            {
                entity.HasKey(e => e.CdCidade);

                entity.ToTable("cidade");

                entity.Property(e => e.CdCidade).HasColumnName("cd_cidade");

                entity.Property(e => e.CdEstado).HasColumnName("cd_estado");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NmCidade)
                    .IsRequired()
                    .HasColumnName("nm_cidade")
                    .HasColumnType("character varying(120)");

                entity.HasOne(d => d.CdEstadoNavigation)
                    .WithMany(p => p.Cidade)
                    .HasForeignKey(d => d.CdEstado)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("estado_cidade_fk");
            });

            modelBuilder.Entity<Endereco>(entity =>
            {
                entity.HasKey(e => e.CdEndereco);

                entity.ToTable("endereco");

                entity.Property(e => e.CdEndereco).HasColumnName("cd_endereco");

                entity.Property(e => e.CdCidade).HasColumnName("cd_cidade");

                entity.Property(e => e.DsBairro)
                    .IsRequired()
                    .HasColumnName("ds_bairro")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DsComplemento)
                    .IsRequired()
                    .HasColumnName("ds_complemento")
                    .HasColumnType("character varying(120)");

                entity.Property(e => e.DsLogradouro)
                    .IsRequired()
                    .HasColumnName("ds_logradouro")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DsNumero)
                    .IsRequired()
                    .HasColumnName("ds_numero")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NrCep)
                    .IsRequired()
                    .HasColumnName("nr_cep")
                    .HasColumnType("character varying(10)");

                entity.HasOne(d => d.CdCidadeNavigation)
                    .WithMany(p => p.Endereco)
                    .HasForeignKey(d => d.CdCidade)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("cidade_endereco_fk");
            });

            modelBuilder.Entity<Estado>(entity =>
            {
                entity.HasKey(e => e.CdEstado);

                entity.ToTable("estado");

                entity.Property(e => e.CdEstado).HasColumnName("cd_estado");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NmEstado)
                    .IsRequired()
                    .HasColumnName("nm_estado")
                    .HasColumnType("character varying(120)");

                entity.Property(e => e.SgEstado)
                    .IsRequired()
                    .HasColumnName("sg_estado")
                    .HasColumnType("character varying(5)");
            });

            modelBuilder.Entity<EstadoOcorrencia>(entity =>
            {
                entity.HasKey(e => e.CdEstadoocorrencia);

                entity.ToTable("estado_ocorrencia");

                entity.Property(e => e.CdEstadoocorrencia).HasColumnName("cd_estadoocorrencia");

                entity.Property(e => e.DsEstadoocorrencia)
                    .IsRequired()
                    .HasColumnName("ds_estadoocorrencia")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");
            });

            modelBuilder.Entity<HistoricoOcorrencia>(entity =>
            {
                entity.HasKey(e => e.CdHistoricoocorrencia);

                entity.ToTable("historico_ocorrencia");

                entity.Property(e => e.CdHistoricoocorrencia).HasColumnName("cd_historicoocorrencia");

                entity.Property(e => e.CdOcorrencia).HasColumnName("cd_ocorrencia");

                entity.Property(e => e.DsHistoricoocorrencia)
                    .IsRequired()
                    .HasColumnName("ds_historicoocorrencia")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.HasOne(d => d.CdOcorrenciaNavigation)
                    .WithMany(p => p.HistoricoOcorrencia)
                    .HasForeignKey(d => d.CdOcorrencia)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("ocorrencia_historico_ocorrencia_fk");
            });

            modelBuilder.Entity<HistoricoSenha>(entity =>
            {
                entity.HasKey(e => e.CdHistoricosenha);

                entity.ToTable("historico_senha");

                entity.Property(e => e.CdHistoricosenha)
                    .HasColumnName("cd_historicosenha")
                    .HasDefaultValueSql("nextval('historico_senha_cd_senha_seq'::regclass)");

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DsSenha)
                    .IsRequired()
                    .HasColumnName("ds_senha")
                    .HasColumnType("character varying(30)");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.HasOne(d => d.CdUsuarioNavigation)
                    .WithMany(p => p.HistoricoSenha)
                    .HasForeignKey(d => d.CdUsuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("usuario_historico_senha_fk");
            });

            modelBuilder.Entity<ImagemOcorrencia>(entity =>
            {
                entity.HasKey(e => e.CdImagem);

                entity.ToTable("imagem_ocorrencia");

                entity.Property(e => e.CdImagem).HasColumnName("cd_imagem");

                entity.Property(e => e.CdOcorrencia).HasColumnName("cd_ocorrencia");

                entity.Property(e => e.DsCaminho)
                    .IsRequired()
                    .HasColumnName("ds_caminho")
                    .HasColumnType("character varying(500)");

                entity.Property(e => e.DsTipo)
                    .IsRequired()
                    .HasColumnName("ds_tipo")
                    .HasColumnType("character varying(120)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NmImagem)
                    .IsRequired()
                    .HasColumnName("nm_imagem")
                    .HasColumnType("character varying(255)");

                entity.HasOne(d => d.CdOcorrenciaNavigation)
                    .WithMany(p => p.ImagemOcorrencia)
                    .HasForeignKey(d => d.CdOcorrencia)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("ocorrencia_imagem_ocorrencia_fk");
            });

            modelBuilder.Entity<ImagemUsuario>(entity =>
            {
                entity.HasKey(e => e.CdImagem);

                entity.ToTable("imagem_usuario");

                entity.Property(e => e.CdImagem).HasColumnName("cd_imagem");

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DsCaminho)
                    .IsRequired()
                    .HasColumnName("ds_caminho")
                    .HasColumnType("character varying(500)");

                entity.Property(e => e.DsTipo)
                    .IsRequired()
                    .HasColumnName("ds_tipo")
                    .HasColumnType("character varying(120)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NmImagem)
                    .IsRequired()
                    .HasColumnName("nm_imagem")
                    .HasColumnType("character varying(255)");

                entity.HasOne(d => d.CdUsuarioNavigation)
                    .WithMany(p => p.ImagemUsuario)
                    .HasForeignKey(d => d.CdUsuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("usuario_imagem_usuario_fk");
            });

            modelBuilder.Entity<Ocorrencia>(entity =>
            {
                entity.HasKey(e => e.CdOcorrencia);

                entity.ToTable("ocorrencia");

                entity.Property(e => e.CdOcorrencia).HasColumnName("cd_ocorrencia");

                entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

                entity.Property(e => e.CdEndereco).HasColumnName("cd_endereco");

                entity.Property(e => e.CdEstadoocorrencia).HasColumnName("cd_estadoocorrencia");

                entity.Property(e => e.CdPrioridade).HasColumnName("cd_prioridade");

                entity.Property(e => e.CdTipoocorrencia).HasColumnName("cd_tipoocorrencia");

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DsFinalizado).HasColumnName("ds_finalizado");

                entity.Property(e => e.DsMensagem)
                    .IsRequired()
                    .HasColumnName("ds_mensagem")
                    .HasColumnType("character varying(1000)");

                entity.Property(e => e.DsObservacao)
                    .IsRequired()
                    .HasColumnName("ds_observacao")
                    .HasColumnType("character varying(500)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NrCurtir).HasColumnName("nr_curtir");

                entity.Property(e => e.NrOcorrencia)
                    .HasColumnName("nr_ocorrencia")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.NrStatus).HasColumnName("nr_status");

                entity.HasOne(d => d.CdAreaatendimentoNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdAreaatendimento)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("area_atendimento_ocorrencia_fk");

                entity.HasOne(d => d.CdEnderecoNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdEndereco)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("endereco_ocorrencia_fk");

                entity.HasOne(d => d.CdEstadoocorrenciaNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdEstadoocorrencia)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("estado_ocorrencia_ocorrencia_fk");

                entity.HasOne(d => d.CdPrioridadeNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdPrioridade)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("prioridade_ocorrencia_fk");

                entity.HasOne(d => d.CdTipoocorrenciaNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdTipoocorrencia)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("tipo_ocorrencia_ocorrencia_fk");

                entity.HasOne(d => d.CdUsuarioNavigation)
                    .WithMany(p => p.Ocorrencia)
                    .HasForeignKey(d => d.CdUsuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("usuario_ocorrencia_fk");
            });

            modelBuilder.Entity<PrioridadeOcorrencia>(entity =>
            {
                entity.HasKey(e => e.CdPrioridade);

                entity.ToTable("prioridade_ocorrencia");

                entity.Property(e => e.CdPrioridade).HasColumnName("cd_prioridade");

                entity.Property(e => e.DsPrioridade)
                    .IsRequired()
                    .HasColumnName("ds_prioridade")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NrPrioridade).HasColumnName("nr_prioridade");
            });

            modelBuilder.Entity<TelefoneAreaatendimento>(entity =>
            {
                entity.HasKey(e => new { e.CdTelefoneareaatendimento, e.CdAreaatendimento });

                entity.ToTable("telefone_areaatendimento");

                entity.Property(e => e.CdTelefoneareaatendimento)
                    .HasColumnName("cd_telefoneareaatendimento")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

                entity.Property(e => e.DsTelefone)
                    .IsRequired()
                    .HasColumnName("ds_telefone")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NrDdd)
                    .IsRequired()
                    .HasColumnName("nr_ddd")
                    .HasColumnType("character varying(5)");

                entity.Property(e => e.NrTelefone)
                    .IsRequired()
                    .HasColumnName("nr_telefone")
                    .HasColumnType("character varying(20)");

                entity.HasOne(d => d.CdAreaatendimentoNavigation)
                    .WithMany(p => p.TelefoneAreaatendimento)
                    .HasForeignKey(d => d.CdAreaatendimento)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("area_atendimento_telefone_areaatendimento_fk");
            });

            modelBuilder.Entity<TelefoneUsuario>(entity =>
            {
                entity.HasKey(e => new { e.CdTelefone, e.CdUsuario });

                entity.ToTable("telefone_usuario");

                entity.Property(e => e.CdTelefone)
                    .HasColumnName("cd_telefone")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DsTelefone)
                    .IsRequired()
                    .HasColumnName("ds_telefone")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.NrDdd)
                    .IsRequired()
                    .HasColumnName("nr_ddd")
                    .HasColumnType("character varying(5)");

                entity.Property(e => e.NrTelefone)
                    .IsRequired()
                    .HasColumnName("nr_telefone")
                    .HasColumnType("character varying(20)");

                entity.HasOne(d => d.CdUsuarioNavigation)
                    .WithMany(p => p.TelefoneUsuario)
                    .HasForeignKey(d => d.CdUsuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("usuario_telefone_fk");
            });

            modelBuilder.Entity<TipoOcorrencia>(entity =>
            {
                entity.HasKey(e => e.CdTipoocorrencia);

                entity.ToTable("tipo_ocorrencia");

                entity.Property(e => e.CdTipoocorrencia).HasColumnName("cd_tipoocorrencia");

                entity.Property(e => e.DsTipoocorrencia)
                    .IsRequired()
                    .HasColumnName("ds_tipoocorrencia")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");
            });

            modelBuilder.Entity<Usuario>(entity =>
            {
                entity.HasKey(e => e.CdUsuario);

                entity.ToTable("usuario");

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DsEmail)
                    .IsRequired()
                    .HasColumnName("ds_email")
                    .HasColumnType("character varying(80)");

                entity.Property(e => e.DsSenha)
                    .IsRequired()
                    .HasColumnName("ds_senha")
                    .HasColumnType("character varying(30)");

                entity.Property(e => e.DtAtualizacao)
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("date");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.Property(e => e.DtNascimento)
                    .HasColumnName("dt_nascimento")
                    .HasColumnType("date");

                entity.Property(e => e.NmUsuario)
                    .IsRequired()
                    .HasColumnName("nm_usuario")
                    .HasColumnType("character varying(255)");

                entity.Property(e => e.StAdministrador).HasColumnName("st_administrador");

                entity.Property(e => e.StStatus).HasColumnName("st_status");
            });

            modelBuilder.Entity<UsuarioAreaatendimento>(entity =>
            {
                entity.HasKey(e => new { e.CdUsuarioatendimento, e.CdAreaatendimento });

                entity.ToTable("usuario_areaatendimento");

                entity.Property(e => e.CdUsuarioatendimento)
                    .HasColumnName("cd_usuarioatendimento")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.CdAreaatendimento).HasColumnName("cd_areaatendimento");

                entity.Property(e => e.CdUsuario).HasColumnName("cd_usuario");

                entity.Property(e => e.DtAtualizacao)
                    .IsRequired()
                    .HasColumnName("dt_atualizacao")
                    .HasColumnType("character varying");

                entity.Property(e => e.DtCadastro)
                    .HasColumnName("dt_cadastro")
                    .HasColumnType("date");

                entity.HasOne(d => d.CdAreaatendimentoNavigation)
                    .WithMany(p => p.UsuarioAreaatendimento)
                    .HasForeignKey(d => d.CdAreaatendimento)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("area_atendimento_usuario_areaatendimento_fk");

                entity.HasOne(d => d.CdUsuarioNavigation)
                    .WithMany(p => p.UsuarioAreaatendimento)
                    .HasForeignKey(d => d.CdUsuario)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("usuario_usuario_areaatendimento_fk");
            });

            modelBuilder.HasSequence("historico_senha_cd_senha_seq");
        }
    }
}
