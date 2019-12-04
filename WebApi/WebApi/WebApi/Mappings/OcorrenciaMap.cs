using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class OcorrenciaMap : IEntityTypeConfiguration<Ocorrencia>
    {
        public void Configure(EntityTypeBuilder<Ocorrencia> entity)
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
            entity.Property(e => e.DsMsgadmin).HasColumnName("ds_msgadmin");

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
        }
    }
}
