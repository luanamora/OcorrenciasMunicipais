using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApi.Models;

namespace WebApi.Mappings
{
    public class TelefoneAreaAtendimentoMap : IEntityTypeConfiguration<TelefoneAreaatendimento>
    {
        public void Configure(EntityTypeBuilder<TelefoneAreaatendimento> entity)
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
        }
    }
}
